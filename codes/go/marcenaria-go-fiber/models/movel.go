package models

type Movel struct {
	Movel      string      `json:"movel"`
	Material   string      `json:"material"`
	Geometrias []Geometria `json:"geometrias"`
}
