use serde::{Serialize, Deserialize};
use utoipa::ToSchema;

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct Movel {
  pub nome: String,
  pub material: String,
  pub geometrias: Vec<String>,
}
