use ntex::{
  web,
  http::{self, StatusCode},
  util::BytesMut,
};
use crate::{
  models::{
    madeiras::Madeira, moveis::Movel, geometrias::geometria::Geometria,
    final_result::FinalResult,
  },
  exceptions::http_error::HttpError,
};

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
pub async fn get_materiais() -> impl web::Responder {
  let json = simd_json::to_string(&Madeira::get_all_info()).unwrap();
  let body = BytesMut::from(json);

  web::HttpResponse::with_body(http::StatusCode::OK, body.into())
}

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
pub async fn get_geometrias() -> web::HttpResponse {
  let json = simd_json::to_string(&Geometria::get_all_info()).unwrap();
  let body = BytesMut::from(json);

  web::HttpResponse::with_body(http::StatusCode::OK, body.into())
}

#[utoipa::path(
    post,
    path = "/orcamento",
    request_body = Movel,
    responses(
        (status = 201, description = "Orçamento completo do móvel", body = FinalResult),
        (status = 400, description = "Há algo de errado com a requisição", body = HttpError)
    )
)]
pub async fn post_orcamento(
  mut body: web::types::Payload,
) -> impl web::Responder {
  let mut bytes = BytesMut::new();
  while let Some(item) = body.0.recv().await {
    bytes.extend_from_slice(&item.unwrap());
  }

  let movel: Movel = simd_json::from_slice(&mut bytes).unwrap();

  let result = FinalResult::from(&movel);

  match result {
    Ok(final_result) => {
      let json = simd_json::to_string(&final_result).unwrap();
      let body = BytesMut::from(json);

      web::HttpResponse::with_body(http::StatusCode::OK, body.into())
    }
    Err(err) => web::HttpResponse::BadRequest().json(&HttpError {
      status: StatusCode::BAD_REQUEST,
      msg: err,
    }),
  }
}
