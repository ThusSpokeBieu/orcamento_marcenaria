use unicode_normalization::UnicodeNormalization;

pub fn normalize(s: &str) -> String {
  s.nfd().collect::<String>().to_ascii_lowercase()
}

pub fn convert_to_f64(value: &str) -> Result<f64, std::num::ParseFloatError> {
    let cleaned: String = value.chars()
        .filter(|c| c.is_numeric() || *c == ',' || *c == '.')
        .map(|c| if c == ',' { '.' } else { c })
        .collect();
    cleaned.parse::<f64>()
}

