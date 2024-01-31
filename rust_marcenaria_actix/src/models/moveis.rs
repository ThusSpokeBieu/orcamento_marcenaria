use serde::Deserialize;
use smallvec::SmallVec;
use utoipa::ToSchema;

use super::geometrias::Geometria;

#[derive(Deserialize, ToSchema)]
pub struct Movel {
    pub movel: String,
    pub material: String,
    pub geometrias: SmallVec<[Geometria; 8]>,
}
