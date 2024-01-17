use unicode_normalization::UnicodeNormalization;

pub fn normalize(s: &str) -> String {
  s.nfd().collect::<String>().to_ascii_lowercase()
}
