use actix_web::{web::Json, HttpResponse, Responder};

use crate::models::{final_result::FinalResult, moveis::Movel};

#[utoipa::path(
  post,
  path = "/orcamento",
  request_body = Movel,
  responses(
      (status = 201, description = "Orçamento completo do móvel", body = FinalResult),
  )
)]
pub async fn orcamento(json: Json<Movel>) -> impl Responder {
    let result = FinalResult::from(&json);

    match result {
        Ok(orcamento) => HttpResponse::Ok().json(orcamento),
        Err(err) => HttpResponse::BadRequest().body(err.to_string()),
    }
}
