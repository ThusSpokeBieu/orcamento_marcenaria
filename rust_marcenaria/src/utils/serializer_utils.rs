use serde::Serializer;

pub fn serialize_real<S>(x: &f64, s: S) -> Result<S::Ok, S::Error>
where
    S: Serializer,
{
    s.serialize_str(&format!("R${:.2}", x).replace(".", ","))
}
