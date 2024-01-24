use std::f64::consts::PI;

use serde::{Deserialize, Serialize};
use utoipa::ToSchema;

use crate::utils::str_utils;

#[derive(Serialize, Deserialize, ToSchema)]
#[serde(tag = "geometria")]
pub enum Geometria {
    #[serde(alias = "esfera", alias = "Esfera", alias = "ESFERA")]
    Esfera {
        #[serde(skip_serializing)]
        estrutura: String,
        raio: String,
    },
    #[serde(alias = "cubo", alias = "Cubo", alias = "CUBO")]
    Cubo {
        #[serde(skip_serializing)]
        estrutura: String,
        lado: String,
    },
    #[serde(alias = "cilindro", alias = "Cilindro", alias = "CILINDRO")]
    Cilindro {
        #[serde(skip_serializing)]
        estrutura: String,
        raio_base: String,
        altura: String,
    },
    #[serde(
        alias = "paralelepipedo",
        alias = "paralelepípedo",
        alias = "Paralelepípedo",
        alias = "Paralelepipedo",
        alias = "PARALELEPIPEDO",
        alias = "PARALELEPÍPEDO"
    )]
    Paralelepipedo {
        #[serde(skip_serializing)]
        estrutura: String,
        comprimento: String,
        largura: String,
        altura: String,
    },
    Desconhecido,
}

impl Geometria {
    pub fn get_all_info() -> [Geometria; 4] {
        [
            Geometria::Esfera {
                estrutura: String::default(),
                raio: String::from("1,00cm"),
            },
            Geometria::Cubo {
                estrutura: String::default(),
                lado: String::from("1,00cm"),
            },
            Geometria::Cilindro {
                estrutura: String::default(),
                raio_base: String::from("1,00cm"),
                altura: String::from("1,00cm"),
            },
            Geometria::Paralelepipedo {
                estrutura: String::default(),
                comprimento: String::from("1,00cm"),
                largura: String::from("1,00cm"),
                altura: String::from("1,00cm"),
            },
        ]
    }
}

impl Geometria {
    pub fn get_estrutura(&self) -> Option<&str> {
        match self {
            Geometria::Esfera { estrutura, .. } => Some(estrutura),
            Geometria::Cubo { estrutura, .. } => Some(estrutura),
            Geometria::Cilindro { estrutura, .. } => Some(estrutura),
            Geometria::Paralelepipedo { estrutura, .. } => Some(estrutura),
            Geometria::Desconhecido => None,
        }
    }

    pub fn get_name(&self) -> Result<&'static str, &'static str> {
        match self {
            Geometria::Esfera {..} => Ok("Esfera"),
            Geometria::Cubo {..} => Ok("Cubo"),
            Geometria::Cilindro {..} => Ok("Cilindro"),
            Geometria::Paralelepipedo {..} => Ok("Paralelepípedo"),
            _ => Err("Geometria inválida, por favor, utilize apenas geometrias válidas do endpoint /geometrias")
        }
    }

    pub fn get_area(&self) -> Result<f64, String> {
        match self {
            Geometria::Esfera { raio, estrutura } => {
                let r: f64 = str_utils::convert_to_f64(raio).map_err(|_| {
                    format!(
                        "A esfera {} possui o valor do raio inválido: {}",
                        estrutura, raio
                    )
                })?;

                if r <= 0.0 {
                    let err_message = format!(
                        "A esfera {} deve ter um raio positivo, {} é inválido",
                        estrutura, raio
                    );
                    Err(err_message)
                } else {
                    Ok(4.0 * PI * r * r)
                }
            }

            Geometria::Cubo { estrutura, lado } => {
                let a = str_utils::convert_to_f64(lado).map_err(|_| {
                    format!(
                        "O cubo {} está com o valor do lado inválido: {}",
                        estrutura, lado
                    )
                })?;

                if a <= 0.0 {
                    let err_message = format!(
                        "O cubo {} deve ter o lado positivo, {} é inválido",
                        estrutura, lado
                    );
                    Err(err_message)
                } else {
                    Ok(6.0 * a * a)
                }
            }

            Geometria::Cilindro {
                estrutura,
                raio_base,
                altura,
            } => {
                let r = str_utils::convert_to_f64(raio_base).map_err(|_| {
                    format!(
                        "O cilindro {} está com o valor do raio inválido {}",
                        estrutura, raio_base
                    )
                })?;

                let a = str_utils::convert_to_f64(altura).map_err(|_| {
                    format!(
                        "O cilindro {} está com o valor da altura inválida: {}",
                        estrutura, altura
                    )
                })?;

                if a <= 0.0 || r <= 0.0 {
                    let err_message = format!(
            "O cilindro {} deve ter a altura e o raio_base positivos, raio: {} ou altura: {} é inválido",
            estrutura, raio_base, altura
          );
                    Err(err_message)
                } else {
                    Ok(2.0 * PI * r * (r + a))
                }
            }

            Geometria::Paralelepipedo {
                estrutura,
                comprimento,
                largura,
                altura,
            } => {
                let c = str_utils::convert_to_f64(comprimento).map_err(|_| {
                    format!(
                        "O paralelepípedo {} está com o valor do comprimento inválido: {}",
                        *estrutura, *comprimento
                    )
                })?;

                let l = str_utils::convert_to_f64(largura).map_err(|_| {
                    format!(
                        "O paralelepípedo {} está com o valor da largura inválido: {}",
                        *estrutura, *largura
                    )
                })?;

                let a = str_utils::convert_to_f64(altura).map_err(|_| {
                    format!(
                        "O paralelepípedo {} está com o valor da altura inválido: {}",
                        *estrutura, *altura
                    )
                })?;

                if c <= 0.0 || l <= 0.0 || a <= 0.0 {
                    let err_message = format!(
            "O paralelepípedo {} deve ter o comprimento, largura e altura positivos, comprimento: {}, altura: {} ou largura {} é inválido",
            *estrutura, *comprimento, *largura, *altura
        );
                    Err(err_message)
                } else {
                    Ok(2.0 * (c * l + l * a + a * c))
                }
            }

            _ => Err(String::from("err")),
        }
    }
}
