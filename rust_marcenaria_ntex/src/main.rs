#[global_allocator]
static GLOBAL: snmalloc_rs::SnMalloc = snmalloc_rs::SnMalloc;

mod routes;
mod exceptions;
mod models;
mod utils;

use ntex::{
  http,
  util::PoolId,
  web::{self, route},
};

const HOST: &str = "127.0.0.1:8080";

#[ntex::main]
async fn main() -> std::io::Result<()> {
  ntex::server::build()
    .backlog(16 * 2048)
    .workers(num_cpus::get() * 2)
    .bind("Marcenaria", HOST, |cfg| {
      cfg.memory_pool(PoolId::P1);
      PoolId::P1.set_read_params(65535 * 16, 2048 * 16);
      PoolId::P1.set_write_params(65535 * 16, 2048 * 16);

      http::HttpService::build()
        .keep_alive(http::KeepAlive::Os)
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
