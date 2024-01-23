package materiais

import (
	"errors"
	"marcenaria/utils"
)

type Material struct {
	Nome  string  `json:"nome"`
	Preco float64 `json:"preco"`
}

var Materiais = make(map[string]Material)

var (
	Pinho    = Material{"Pinho", 0.10}
	Carvalho = Material{"Carvalho", 0.30}
	Ebano    = Material{"Ebano", 5.00}
)

func init() {
	Materiais["pinho"] = Pinho
	Materiais["carvalho"] = Carvalho
	Materiais["ebano"] = Ebano
}

func GetMaterial(nome string) (Material, error) {
	nome = utils.NormalizeStr(nome)

	material, ok := Materiais[nome]
	if !ok {
		return Material{}, errors.New("Material não encontrado")
	}

	return material, nil
}

func GetMateriais() []Material {
	materiais := make([]Material, 0, len(Materiais))

	for _, material := range Materiais {
		materiais = append(materiais, material)
	}

	return materiais
}
