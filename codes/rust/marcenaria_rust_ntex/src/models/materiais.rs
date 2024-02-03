use std::io::{Error, ErrorKind};

use serde::{Deserialize, Serialize};
use utoipa::ToSchema;

use crate::utils::str_utils;

pub enum Material {
    Pinho,
    Carvalho,
    Ebano,
}

#[derive(Debug, Clone, Serialize, Deserialize, ToSchema)]
pub struct MaterialInfo {
    pub nome: &'static str,
    pub preco_base: &'static str,
}

impl Material {
    pub fn from_str(s: &str) -> Result<Material, Error> {
        match str_utils::normalize(s).as_str() {
            "pinho" => Ok(Material::Pinho),
            "carvalho" => Ok(Material::Carvalho),
            "ebano" => Ok(Material::Ebano),
            _ => Err(Error::new(ErrorKind::InvalidInput, "Material inválida.")),
        }
    }

    pub fn get_info(&self) -> MaterialInfo {
        match self {
            Material::Pinho => MaterialInfo {
                nome: "Pinho",
                preco_base: "R$ 0,10",
            },
            Material::Carvalho => MaterialInfo {
                nome: "Carvalho",
                preco_base: "R$ 0,30",
            },
            Material::Ebano => MaterialInfo {
                nome: "Ebano",
                preco_base: "R$ 5,00",
            },
        }
    }

    pub fn get_all_info() -> [MaterialInfo; 3] {
        [
            Material::Pinho.get_info(),
            Material::Carvalho.get_info(),
            Material::Ebano.get_info(),
        ]
    }

    pub fn get_preco(&self) -> f64 {
        match self {
            Material::Pinho { .. } => 0.10,
            Material::Carvalho { .. } => 0.30,
            Material::Ebano { .. } => 5.00,
        }
    }
}
