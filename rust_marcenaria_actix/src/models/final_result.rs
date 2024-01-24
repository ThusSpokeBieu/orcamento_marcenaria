use serde::Serialize;
use utoipa::ToSchema;
use crate::models::moveis::Movel;

use super::{geometrias::geometria::Geometria, madeiras::Madeira};

#[derive(Serialize, ToSchema)]
pub struct FinalResult<'a> {
  pub tipo_movel: &'a str,
  pub material: &'a str,
  #[serde(serialize_with = "crate::utils::serializer_utils::serialize_real")]
  pub preco_total: f64,
  pub preco_estruturas: Vec<PrecoEstrutura<'a>>,
}

#[derive(Serialize, ToSchema)]
pub struct PrecoEstrutura<'a> {
  pub estrutura: &'a str,
  pub geometria: &'a str,
  #[serde(serialize_with = "crate::utils::serializer_utils::serialize_real")]
  pub preco: f64,
}

impl<'a> FinalResult<'a> {
  pub fn from(movel: &'a Movel) -> Result<FinalResult<'a>, String> {
    let precos = PrecoEstrutura::from(&movel.geometrias, &movel.material);

    match precos {
      Ok(precos_ok) => Ok(FinalResult {
        tipo_movel: &movel.movel,
        material: &movel.material,
        preco_total: precos_ok.iter().map(|p| p.preco).sum(),
        preco_estruturas: precos_ok,
      }),

      Err(err) => Err(err),
    }
  }
}

impl<'a> PrecoEstrutura<'a> {
  pub fn from(
    estruturas: &'a [Geometria],
    material: &'a str,
  ) -> Result<Vec<PrecoEstrutura<'a>>, String> {
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
          estrutura: estrutura.get_estrutura().unwrap(),
          geometria: estrutura.get_name().unwrap(),
          preco: madeira.get_preco() * estrutura.get_area().unwrap(),
        })
        .collect(),
    )
  }
}
