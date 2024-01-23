use ntex::{
  web,
  http::{
    self,
    header::{SERVER, CONTENT_TYPE},
    StatusCode,
  },
  util::BytesMut,
};
use crate::{
  models::{
    madeiras::Madeira, moveis::Movel, geometrias::geometria::Geometria,
    final_result::FinalResult,
  },
  utils::web_utils,
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
  let json = serde_json::to_string(&Madeira::get_all_info()).unwrap();
  let body = BytesMut::from(json);

  let mut response =
    web::HttpResponse::with_body(http::StatusCode::OK, body.into());
  response.headers_mut().insert(SERVER, web_utils::HDR_SERVER);
  response
    .headers_mut()
    .insert(CONTENT_TYPE, web_utils::HDR_JSON_CONTENT_TYPE);

  response
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
  let json = serde_json::to_string(&Geometria::get_all_info()).unwrap();
  let body = BytesMut::from(json);

  let mut response =
    web::HttpResponse::with_body(http::StatusCode::OK, body.into());
  response.headers_mut().insert(SERVER, web_utils::HDR_SERVER);
  response
    .headers_mut()
    .insert(CONTENT_TYPE, web_utils::HDR_JSON_CONTENT_TYPE);

  response
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
  req_body: web::types::Json<Movel>,
) -> impl web::Responder {
  let result = FinalResult::from(&req_body.0);

  match result {
    Ok(final_result) => web::HttpResponse::Ok().json(&final_result),
    Err(err) => web::HttpResponse::BadRequest().json(&HttpError {
      status: StatusCode::BAD_REQUEST,
      msg: err,
    }),
  }
}
