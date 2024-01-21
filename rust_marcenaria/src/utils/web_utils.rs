use ntex::http::header::HeaderValue;

pub const HDR_SERVER: HeaderValue = HeaderValue::from_static("MARCENARIA");
pub const HDR_JSON_CONTENT_TYPE: HeaderValue = HeaderValue::from_static("application/json");
