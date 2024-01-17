use serde::{Serialize, Deserialize};
use utoipa::ToSchema;

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct FinalResult {
    pub nome_movel: String,
    pub material: String,
    pub preco_total: String,
    pub preco_estruturas: Vec<PrecoEstrutura>,
}

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct PrecoEstrutura {
    pub estrutura: String,
    pub geometria: String,
    pub preco: String,
}
