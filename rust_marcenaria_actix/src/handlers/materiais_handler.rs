use actix_web::{HttpResponse, Responder};

use crate::models::materiais::Material;

#[utoipa::path(
  get,
  path = "/materiais",
  responses(
    (
      status = 200,
      description = "Lista de materiais disponíveis",
      body = [MaterialInfo])
  )
)]
pub async fn materiais() -> impl Responder {
    HttpResponse::Ok().json(&Material::get_all_info())
}
