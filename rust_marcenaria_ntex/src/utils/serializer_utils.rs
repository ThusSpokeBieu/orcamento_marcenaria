pub mod centimeter_str {
    use core::f64;
    use serde::{de::Error, Deserialize, Deserializer, Serializer};

    pub fn serialize<S>(value: &f64, serializer: S) -> Result<S::Ok, S::Error>
    where
        S: Serializer,
    {
        serializer.serialize_str(&format!("{:.2}cm", value).replace('.', ","))
    }

    pub fn deserialize<'de, D>(deserializer: D) -> Result<f64, D::Error>
    where
        D: Deserializer<'de>,
    {
        let value = String::deserialize(deserializer)?;
        let value = value.replace(",", ".");
        match fast_float::parse_partial::<f64, _>(value) {
            Ok(value) => Ok(value.0),
            Err(_) => Err(Error::custom(
                "Centimetro deve ser númerico ou no formato 'x,xxcm'",
            )),
        }
    }
}

pub mod real_str {
    use serde::Serializer;

    pub fn serialize<S>(value: &f64, serializer: S) -> Result<S::Ok, S::Error>
    where
        S: Serializer,
    {
        serializer.serialize_str(&format!("R${:.2}", value).replace('.', ","))
    }
}
