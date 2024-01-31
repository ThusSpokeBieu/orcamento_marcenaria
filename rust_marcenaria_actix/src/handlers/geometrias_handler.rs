use actix_web::{HttpResponse, Responder};

use crate::models::geometrias::Geometria;

#[utoipa::path(
  get,
  path = "/geometrias",
  responses(
    (
      status = 200,
      description = "Lista de geometrias de estrutura disponíveis",
      body = [Geometria])
  )
)]
pub async fn geometrias() -> impl Responder {
    HttpResponse::Ok().json(Geometria::get_geometrias())
}
