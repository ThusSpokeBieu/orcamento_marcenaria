use serde::{Serialize, Deserialize};
use utoipa::ToSchema;
use crate::models::moveis::Movel;

use super::{geometrias::geometria::Geometria, madeiras::Madeira};

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct FinalResult {
  pub tipo_movel: String,
  pub material: String,
  #[serde(serialize_with = "crate::utils::serializer_utils::serialize_real")]
  pub preco_total: f64,
  pub preco_estruturas: Vec<PrecoEstrutura>,
}

#[derive(Clone, Debug, Serialize, Deserialize, ToSchema)]
pub struct PrecoEstrutura {
  pub estrutura: String,
  pub geometria: String,
  #[serde(serialize_with = "crate::utils::serializer_utils::serialize_real")]
  pub preco: f64,
}

impl FinalResult {
  pub fn from(movel: &Movel) -> Result<FinalResult, String> {
    let precos = PrecoEstrutura::from(&movel.geometrias, &movel.material);

    match precos {
      Ok(precos_ok) => Ok(FinalResult {
        tipo_movel: movel.movel.clone(),
        material: movel.material.clone(),
        preco_total: precos_ok.iter().map(|p| p.preco).sum(),
        preco_estruturas: precos_ok,
      }),

      Err(err) => Err(err),
    }
  }
}

impl PrecoEstrutura {
  pub fn from(
    estruturas: &[Geometria],
    material: &str,
  ) -> Result<Vec<PrecoEstrutura>, String> {
    let madeira = Madeira::from_str(material).map_err(|_| {
      format!(
        "{} não é um material válido, escolha entre Ébano, Carvalho e Pinho.",
        material
      )
    })?;

    Ok(
      estruturas
        .iter()
        .map(|estrutura| PrecoEstrutura {
          estrutura: estrutura.get_estrutura().unwrap().to_string(),
          geometria: String::from(estrutura.get_name().unwrap()),
          preco: madeira.get_preco() * estrutura.get_area().unwrap(),
        })
        .collect(),
    )
  }
}
