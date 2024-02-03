use actix_http::StatusCode;
use actix_web::{
    http::header::ContentType,
    web::{Buf, Bytes, BytesMut, Payload},
    Error, HttpResponse,
};
use tokio_stream::StreamExt;

use crate::models::{moveis::Movel, orcamento::Orcamento};

#[utoipa::path(
  post,
  path = "/orcamento",
  request_body = Movel,
  responses(
      (status = 201, description = "Orçamento completo do móvel", body = FinalResult),
  )
)]

pub async fn orcamento(mut payload: Payload) -> Result<HttpResponse, Error> {
    let mut body = BytesMut::new();
    while let Some(chunk) = payload.next().await {
        let chunk = chunk?;
        body.extend_from_slice(&chunk);
    }

    let movel = simd_json::serde::from_slice::<Movel>(body.as_mut()).unwrap();
    let orcamento = Orcamento::from(&movel).unwrap();

    let response = simd_json::serde::to_vec(&orcamento).unwrap();

    Ok(HttpResponse::Created()
        .content_type(ContentType::json())
        .body(response))
}

/*pub async fn orcamento(json: Json<Movel>) -> impl Responder {
    let result = Orcamento::from(&json);

    match result {
        Ok(orcamento) => HttpResponse::Ok().json(&orcamento),
        Err(err) => HttpResponse::BadRequest().body(err.to_string()),
    }
}*/
