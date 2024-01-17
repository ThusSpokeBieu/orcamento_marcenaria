pub(crate) mod orcamento;
pub(crate) mod openapi;

use ntex::web;

pub async fn default() -> web::HttpResponse {
  web::HttpResponse::NotFound().finish()
}
