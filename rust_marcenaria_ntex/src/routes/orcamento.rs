use std::borrow::Borrow;

use ntex::{
  self, util::BytesMut, http::{self, StatusCode}, web
};

use crate::models::{geometrias::Geometria, materiais::Material, moveis::Movel, orcamento::Orcamento};

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
  let json = simd_json::to_string(&Material::get_all_info()).unwrap();
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
  let json = simd_json::to_string(&Geometria::get_geometrias()).unwrap();
  let body = BytesMut::from(json);

  web::HttpResponse::with_body(http::StatusCode::OK, body.into())
}

//simd
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
  mut payload: web::types::Payload,
) -> Result<web::HttpResponse, web::Error> {
    let mut bytes = BytesMut::new();
    while let Some(chunk) = payload.recv().await {
        let chunk = chunk?;
        bytes.extend_from_slice(&chunk);
    }
 
    let movel = simd_json::serde::from_slice::<Movel>(bytes.as_mut()).unwrap();
    let orcamento = Orcamento::from(&movel).unwrap();

    let response = simd_json::serde::to_vec(&orcamento).unwrap();

    Ok(web::HttpResponse::with_body(StatusCode::CREATED, response.into()))
}

//normal json
/*
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
  movel: web::types::Json<Movel>,
) -> web::HttpResponse {
    match Orcamento::from(&movel) {
        Ok(orcamento) => web::HttpResponse::Ok().json(&orcamento),
        Err(err) => web::HttpResponse::BadRequest().json(&HttpError{ 
            status: StatusCode::BAD_REQUEST, 
            msg: err.to_string()
        })
    }
}
*/
