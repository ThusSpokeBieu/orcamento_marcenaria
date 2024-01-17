use std::io::{Error, ErrorKind};

use serde::{Serialize, Deserialize};
use utoipa::ToSchema;

use crate::utils::str_utils;

pub enum Madeira {
  Pinho,
  Carvalho,
  Ebano,
}

#[derive(Debug, Clone, Serialize, Deserialize, ToSchema)]
pub struct MadeiraInfo {
  nome: String,
  preco_base: f64,
}

impl Madeira {
  pub fn from_str(s: &str) -> Result<Madeira, Error> {
    match str_utils::normalize(s).as_str() {
      "pinho" => Ok(Madeira::Pinho),
      "carvalho" => Ok(Madeira::Carvalho),
      "ebano" => Ok(Madeira::Ebano),
      _ => Err(Error::new(ErrorKind::InvalidInput, "Madeira inválida.")),
    }
  }

  pub fn get_info(&self) -> MadeiraInfo {
    match self {
      Madeira::Pinho => MadeiraInfo {
        nome: String::from("Pinho"),
        preco_base: 0.10,
      },
      Madeira::Carvalho => MadeiraInfo {
        nome: String::from("Carvalho"),
        preco_base: 0.30,
      },
      Madeira::Ebano => MadeiraInfo {
        nome: String::from("Ebano"),
        preco_base: 5.00,
      },
    }
  }

  pub fn get_all_info() -> [MadeiraInfo; 3] {
    [
      Madeira::Pinho.get_info(),
      Madeira::Carvalho.get_info(),
      Madeira::Ebano.get_info(),
    ]
  }
}
