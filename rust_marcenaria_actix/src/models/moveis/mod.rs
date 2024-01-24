use serde::Deserialize;
use utoipa::ToSchema;

use super::geometrias::geometria::Geometria;

#[derive(Deserialize, ToSchema)]
pub struct Movel {
  pub movel: String,
  pub material: String,
  pub geometrias: Vec<Geometria>,
}
