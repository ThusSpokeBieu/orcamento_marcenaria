package models

import (
	"errors"
	"fmt"

	"marcenaria/utils"
)

type Material struct {
	Nome  string `json:"nome"`
	Preco string `json:"preco_base"`
}

var Materiais = make(map[string]Material)

var (
	Pinho    = Material{"Pinho", "R$0,10"}
	Carvalho = Material{"Carvalho", "R$0,30"}
	Ebano    = Material{"Ebano", "R$5,00"}
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
		errMessage := fmt.Sprintf(
			"Material %s não é válido escolha entre: Pinho, Carvalho e Ébano",
			nome,
		)
		return Material{}, errors.New(errMessage)
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
