use actix_web::{HttpResponse, Responder};

use crate::models::madeiras::Madeira;

#[utoipa::path(
  get,
  path = "/materiais",
  responses(
    (
      status = 200,
      description = "Lista de materiais disponíveis",
      body = [MadeiraInfo])
  )
)]
pub async fn materiais() -> impl Responder {
    HttpResponse::Ok().json(&Madeira::get_all_info())
}
