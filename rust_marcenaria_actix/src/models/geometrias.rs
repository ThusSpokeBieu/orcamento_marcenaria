use crate::utils::{
    num_utils::{FOUR_PI, TWO_PI},
    serializer_utils::centimeter_str,
};
use serde::{Deserialize, Serialize};
use utoipa::ToSchema;

#[derive(Serialize, Deserialize, ToSchema)]
#[serde(tag = "geometria")]
pub enum Geometria {
    #[serde(alias = "esfera", alias = "Esfera", alias = "ESFERA")]
    Esfera {
        #[serde(skip_serializing)]
        estrutura: String,
        #[serde(with = "centimeter_str")]
        raio: f64,
    },

    #[serde(alias = "cubo", alias = "Cubo", alias = "CUBO")]
    Cubo {
        #[serde(skip_serializing)]
        estrutura: String,
        #[serde(with = "centimeter_str")]
        lado: f64,
    },

    #[serde(alias = "cilindro", alias = "Cilindro", alias = "CILINDRO")]
    Cilindro {
        #[serde(skip_serializing)]
        estrutura: String,
        #[serde(with = "centimeter_str")]
        raio_base: f64,
        #[serde(with = "centimeter_str")]
        altura: f64,
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
        #[serde(with = "centimeter_str")]
        largura: f64,
        #[serde(with = "centimeter_str")]
        altura: f64,
        #[serde(with = "centimeter_str")]
        comprimento: f64,
    },
}

impl Geometria {
    pub fn get_estrutura(&self) -> &str {
        match self {
            Geometria::Esfera { estrutura, .. } => estrutura,
            Geometria::Cubo { estrutura, .. } => estrutura,
            Geometria::Cilindro { estrutura, .. } => estrutura,
            Geometria::Paralelepipedo { estrutura, .. } => estrutura,
        }
    }

    pub fn get_geometria(&self) -> &'static str {
        match self {
            Geometria::Esfera { .. } => "Esfera",
            Geometria::Cubo { .. } => "Cubo",
            Geometria::Cilindro { .. } => "Cilindro",
            Geometria::Paralelepipedo { .. } => "Paralelepípedo",
        }
    }

    pub fn get_area(&self) -> f64 {
        match self {
            Geometria::Esfera { raio, .. } => FOUR_PI * raio.powf(2.0),

            Geometria::Cubo { lado, .. } => 6.0 * lado.powf(2.0),

            Geometria::Cilindro {
                raio_base, altura, ..
            } => TWO_PI * raio_base * (raio_base * altura),

            Geometria::Paralelepipedo {
                comprimento,
                largura,
                altura,
                ..
            } => 2.0 * (comprimento * largura + comprimento * altura + largura * altura),
        }
    }

    pub fn get_geometrias() -> [Geometria; 4] {
        [
            Geometria::Esfera {
                estrutura: String::default(),
                raio: 1.0,
            },
            Geometria::Cubo {
                estrutura: String::default(),
                lado: 1.0,
            },
            Geometria::Cilindro {
                estrutura: String::default(),
                raio_base: 1.0,
                altura: 1.0,
            },
            Geometria::Paralelepipedo {
                estrutura: String::default(),
                comprimento: 1.0,
                largura: 1.0,
                altura: 1.0,
            },
        ]
    }
}
