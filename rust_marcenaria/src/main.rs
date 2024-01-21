#[global_allocator]
static GLOBAL: snmalloc_rs::SnMalloc = snmalloc_rs::SnMalloc;

mod routes;
mod exceptions;
mod models;
mod utils;

use ntex::{
  web::{self, route},
  util::PoolId,
  http,
  time::Seconds,
};

#[ntex::main]
async fn main() -> std::io::Result<()> {
  ntex::server::build()
    .backlog(1024)
    .workers(num_cpus::get())
    .bind("marcenaria", "0.0.0.0:8080", |cfg| {
      cfg.memory_pool(PoolId::P1);
      PoolId::P1.set_read_params(65535, 2048);
      PoolId::P1.set_write_params(65535, 2048);

      http::HttpService::build()
        .keep_alive(http::KeepAlive::Os)
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
    .run()
    .await
}
