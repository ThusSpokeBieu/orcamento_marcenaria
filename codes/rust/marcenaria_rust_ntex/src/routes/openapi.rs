use std::sync::Arc;

use ntex::web;
use ntex::http;
use ntex::util::Bytes;
use utoipa::OpenApi;

use crate::exceptions::http_error::HttpError;
use crate::models::materiais::MaterialInfo;
use crate::models::moveis::Movel;
use crate::models::orcamento::{Orcamento, EstruturaValor};
use crate::models::geometrias::Geometria;
use super::orcamento;

#[derive(OpenApi)]
#[openapi(
  paths(orcamento::get_materiais,
    orcamento::post_orcamento,
    orcamento::get_geometrias),
  components(schemas(MaterialInfo, HttpError, Movel, Orcamento, EstruturaValor, Geometria))
)]
pub(crate) struct ApiDoc;

#[web::get("/{tail}*")]
async fn get_swagger(
  tail: web::types::Path<String>,
  openapi_conf: web::types::State<Arc<utoipa_swagger_ui::Config<'static>>>,
) -> Result<web::HttpResponse, HttpError> {
  if tail.as_ref() == "swagger.json" {
    let spec = ApiDoc::openapi().to_json().map_err(|err| HttpError {
      status: http::StatusCode::INTERNAL_SERVER_ERROR,
      msg: format!("Error generating OpenAPI spec: {}", err),
    })?;
    return Ok(
      web::HttpResponse::Ok()
        .content_type("application/json")
        .body(spec),
    );
  }
  let conf = openapi_conf.as_ref().clone();
  match utoipa_swagger_ui::serve(&tail, conf.into()).map_err(|err| {
    HttpError {
      msg: format!("Error serving Swagger UI: {}", err),
      status: http::StatusCode::INTERNAL_SERVER_ERROR,
    }
  })? {
    None => Err(HttpError {
      status: http::StatusCode::NOT_FOUND,
      msg: format!("path not found: {}", tail),
    }),
    Some(file) => Ok({
      let bytes = Bytes::from(file.bytes.to_vec());
      web::HttpResponse::Ok()
        .content_type(file.content_type)
        .body(bytes)
    }),
  }
}

pub fn ntex_config(config: &mut web::ServiceConfig) {
  let swagger_config = Arc::new(
    utoipa_swagger_ui::Config::new(["/docs/swagger.json"])
      .use_base_layout(),
  );
  config.service(
    web::scope("/docs/")
      .state(swagger_config)
      .service(get_swagger),
  );
}
