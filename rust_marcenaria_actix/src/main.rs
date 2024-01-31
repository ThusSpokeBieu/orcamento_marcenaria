#[global_allocator]
static ALLOC: snmalloc_rs::SnMalloc = snmalloc_rs::SnMalloc;

mod handlers;
mod models;
mod utils;

use std::time::Duration;

use actix_http::{HttpService, KeepAlive};
use actix_service::map_config;
use actix_web::{
    dev::{AppConfig, Server},
    web::{self},
    App,
};

use models::{
    geometrias::Geometria,
    materiais::MaterialInfo,
    moveis::Movel,
    orcamento::{EstruturaValor, Orcamento},
};

use handlers::{geometrias_handler, materiais_handler, orcamento_handler};
use utoipa::OpenApi;
use utoipa_rapidoc::RapiDoc;
use utoipa_redoc::{Redoc, Servable};
use utoipa_swagger_ui::SwaggerUi;

#[derive(OpenApi)]
#[openapi(
    paths(
        geometrias_handler::geometrias,
        materiais_handler::materiais,
        orcamento_handler::orcamento
    ),
    components(schemas(MaterialInfo, Geometria, Movel, Orcamento, EstruturaValor))
)]
struct ApiDoc;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    println!("Started HTTP server: 127.0.0.1:8080");

    let openapi = ApiDoc::openapi();

    env_logger::init_from_env(env_logger::Env::new().default_filter_or("info"));

    Server::build()
        .backlog(16500)
        .workers(num_cpus::get())
        .bind("Marcenaria", "0.0.0.0:8080", move || {
            HttpService::build()
                .keep_alive(KeepAlive::Timeout(Duration::ZERO))
                .h1(map_config(
                    App::new()
                        .service(Redoc::with_url("/redoc", openapi.clone()))
                        .service(
                            SwaggerUi::new("/swagger-ui/{_:.*}")
                                .url("/api-docs/openapi.json", openapi.clone()),
                        )
                        .service(RapiDoc::new("/api-docs/openapi.json").path("/rapidoc"))
                        .route("/geometrias", web::get().to(geometrias_handler::geometrias))
                        .route("/materiais", web::get().to(materiais_handler::materiais))
                        .route("/orcamento", web::post().to(orcamento_handler::orcamento)),
                    |_| AppConfig::default(),
                ))
                .tcp()
        })?
        .run()
        .await
}
