use serde::Serialize;
use smallvec::SmallVec;
use utoipa::ToSchema;

use super::{materiais::Material, moveis::Movel};

#[derive(Serialize, ToSchema)]
pub struct Orcamento {
    pub movel: String,
    pub material: String,
    pub preco_total: f64,
    pub estruturas: SmallVec<[EstruturaValor; 8]>,
}

#[derive(Serialize, ToSchema)]
pub struct EstruturaValor {
    pub estrutura: String,
    pub geometria: String,
    pub valor: f64,
}

impl Orcamento {
    pub fn from(movel: &Movel) -> Result<Orcamento, std::io::Error> {
        let material = Material::from_str(&movel.material)?;

        let estruturas: SmallVec<[EstruturaValor; 8]> = movel
            .geometrias
            .iter()
            .map(|g| {
                let preco = g.get_area() * material.get_preco();

                EstruturaValor {
                    estrutura: g.get_estrutura().to_string(),
                    geometria: g.get_geometria().to_string(),
                    valor: preco,
                }
            })
            .collect();

        let preco_total: f64 = estruturas
            .iter()
            .fold(0.0, |acc, estrutura| acc + estrutura.valor);

        Ok(Orcamento {
            movel: movel.movel.clone(),
            material: movel.material.clone(),
            estruturas,
            preco_total,
        })
    }
}
