package models

import "marcenaria/utils"

type Orcamento struct {
	Movel      string
	Material   string
	PrecoTotal string
	Estruturas []EstruturaValor
}

type EstruturaValor struct {
	Estrutura string
	Geometria string
	Valor     string
}

func NewOrcamento(movel *Movel) (Orcamento, []error) {
	var precoTotal float64
	errList := make([]error, 0)
	estruturas := make([]EstruturaValor, 0)

	material, err := GetMaterial(movel.Material)
	if err != nil {
		errList = append(errList, err)
	}

	for _, geometria := range movel.Geometrias {
		preco, err := geometria.GetPreco(&material)
		if err != nil {
			errList = append(errList, err...)
			continue
		}

		precoTotal += preco

		estrutura := EstruturaValor{
			Estrutura: geometria.Estrutura,
			Geometria: geometria.Geometria,
			Valor:     utils.FormatCurrency(preco),
		}

		estruturas = append(estruturas, estrutura)
	}

	if len(errList) > 0 {
		return Orcamento{}, errList
	}

	return Orcamento{
		Movel:      *&movel.Movel,
		Material:   *&movel.Material,
		PrecoTotal: utils.FormatCurrency(precoTotal),
		Estruturas: estruturas,
	}, nil
}
