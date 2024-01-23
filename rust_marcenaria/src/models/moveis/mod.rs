use serde::{Serialize, Deserialize};
use utoipa::ToSchema;

use super::geometrias::geometria::Geometria;

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct Movel {
  pub movel: String,
  pub material: String,
  pub geometrias: Vec<Geometria>,
}
