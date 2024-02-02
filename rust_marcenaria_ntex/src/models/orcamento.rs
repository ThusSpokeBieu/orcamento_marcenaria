use serde::Serialize;
use smallvec::SmallVec;
use utoipa::ToSchema;

use super::{materiais::Material, moveis::Movel};

#[derive(Serialize, ToSchema)]
pub struct Orcamento<'a> {
    pub movel: &'a str,
    pub material: &'a str,
    pub preco_total: f64,
    pub estruturas: SmallVec<[EstruturaValor<'a>; 8]>,
}

#[derive(Serialize, ToSchema)]
pub struct EstruturaValor<'a> {
    pub estrutura: &'a str,
    pub geometria: &'a str,
    pub valor: f64,
}

impl<'a> Orcamento<'a> {
    pub fn from(movel: &'a Movel) -> Result<Orcamento<'a>, std::io::Error> {
        let material = Material::from_str(&movel.material)?;

        let estruturas: SmallVec<[EstruturaValor<'a>; 8]> = movel
            .geometrias
            .iter()
            .map(|g| {
                let preco = g.get_area() * material.get_preco();

                EstruturaValor {
                    estrutura: g.get_estrutura(),
                    geometria: g.get_geometria(),
                    valor: preco,
                }
            })
            .collect();

        let preco_total: f64 = estruturas
            .iter()
            .fold(0.0, |acc, estrutura| acc + estrutura.valor);

        Ok(Orcamento {
            movel: &movel.movel,
            material: &movel.material,
            estruturas,
            preco_total,
        })
    }
}
