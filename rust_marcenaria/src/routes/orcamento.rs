use ntex::web;
use crate::models::madeiras::Madeira;

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
  web::HttpResponse::Ok().finish()
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
