use ntex::{web, http::{self, header::{SERVER, CONTENT_TYPE}}, util::{BytesMut, ByteString}};
use serde::Serialize;
use crate::{models::madeiras::Madeira, utils::web_utils};

#[utoipa::path(
  get,
  path = "/orcamento/materiais",
  responses(
    (
      status = 200,
      description = "Lista de materiais disponíveis",
      body = [MadeiraInfo])
  )
)]
#[web::get("/orcamento/materiais")]
pub async fn get_materiais() -> web::HttpResponse {
  let json = serde_json::to_string(&Madeira::get_all_info()).unwrap();
  let body = BytesMut::from(json);

  let mut response = web::HttpResponse::with_body(http::StatusCode::OK, body.into());
  response.headers_mut().insert(SERVER, web_utils::HDR_SERVER);
  response.headers_mut().insert(CONTENT_TYPE, web_utils::HDR_JSON_CONTENT_TYPE);
  
  response
}

#[web::get("/orcamento/geometrias")]
pub async fn get_geometria() -> web::HttpResponse {
  web::HttpResponse::Ok().finish()
}

#[web::post("/orcamento")]
pub async fn post_orcamento() -> web::HttpResponse {
  web::HttpResponse::Ok().finish()
}

pub fn ntex_config(cfg: &mut web::ServiceConfig) {
  cfg.service(get_materiais);
  cfg.service(get_geometria);
  cfg.service(post_orcamento);
}
