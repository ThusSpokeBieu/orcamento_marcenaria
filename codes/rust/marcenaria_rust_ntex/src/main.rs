#[global_allocator]
static GLOBAL: snmalloc_rs::SnMalloc = snmalloc_rs::SnMalloc;

mod routes;
mod exceptions;
mod models;
mod utils;

use ntex::{
  http, time::Seconds, util::PoolId, web::{self, route}
};

const HOST: &str = "127.0.0.1:8080";

#[ntex::main]
async fn main() -> std::io::Result<()> {
  ntex::server::build()
    .backlog(16805)
    .workers(num_cpus::get())
    .bind("Marcenaria", HOST, |cfg| {
      cfg.memory_pool(PoolId::P1);
      PoolId::P1.set_read_params(65535, 2048);
      PoolId::P1.set_write_params(65535, 2048);

      http::HttpService::build()
        .keep_alive(http::KeepAlive::Timeout(Seconds::new(60)))
        .client_timeout(Seconds::ZERO)
        .headers_read_rate(Seconds::ZERO, Seconds::ZERO, 0)
        .payload_read_rate(Seconds::ZERO, Seconds::ZERO, 0)
        .h1(
          web::App::new()
            .configure(routes::openapi::ntex_config)
            .route(
              "/materiais",
              web::get().to(routes::orcamento::get_materiais),
            )
            .route(
              "/geometrias",
              web::get().to(routes::orcamento::get_geometrias),
            )
            .route(
              "/orcamento",
              web::post().to(routes::orcamento::post_orcamento),
            )
            .default_service(route().to(routes::default)),
        )
    })
    .unwrap()
    .on_worker_start(|_| async move {
      println!("🚀 Worker is listening to {}", HOST);
      Ok::<_, std::io::Error>(())
    })
    .run()
    .await
}
