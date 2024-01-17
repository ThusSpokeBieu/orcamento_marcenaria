mod routes;
mod exceptions;
mod models;
mod utils;

use ntex::web::{self, App, route};

#[web::get("/")]
async fn index() -> &'static str {
  println!("Recebendo uma requisição... ");
  "Hello world!"
}

#[ntex::main]
async fn main() -> std::io::Result<()> {
  web::server(|| {
    App::new()
      .configure(routes::openapi::ntex_config)
      .configure(routes::orcamento::ntex_config)
      .default_service(route().to(routes::default))
  })
  .bind(("0.0.0.0", 8080))?
  .run()
  .await?;
  Ok(())
}
