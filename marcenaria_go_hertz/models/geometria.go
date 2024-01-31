package models

import (
	"errors"
	"fmt"

	"marcenaria_go_hertz/utils"
)

type Geometria struct {
	Estrutura   string `json:"estrutura,omitempty"`
	Geometria   string `json:"geometria"`
	RaioBase    string `json:"raio_base,omitempty"`
	Lado        string `json:"lado,omitempty"`
	Raio        string `json:"raio,omitempty"`
	Altura      string `json:"altura,omitempty"`
	Comprimento string `json:"comprimento,omitempty"`
	Largura     string `json:"largura,omitempty"`
}

const defaultCm = "5,0cm"

var Geometrias = [4]Geometria{
	{
		Geometria: "Esfera",
		Raio:      defaultCm,
	},
	{
		Geometria: "Cubo",
		Lado:      defaultCm,
	},
	{
		Geometria: "Cilindro",
		RaioBase:  defaultCm,
		Altura:    defaultCm,
	},
	{
		Geometria:   "Paralelepipedo",
		Comprimento: defaultCm,
		Largura:     defaultCm,
		Altura:      defaultCm,
	},
}

func (g Geometria) GetPreco(material *Material) (float64, []error) {
	area, errList := g.GetArea()

	if errList != nil {
		return 0, errList
	}

	precoMaterial, err := utils.ExtractDecimal(material.Preco)
	if err != nil {
		errList = append(errList, err)
		return 0, errList
	}

	preco := area * precoMaterial

	return preco, nil
}

func (g Geometria) GetArea() (float64, []error) {
	errList := make([]error, 0)
	geometria := utils.NormalizeStr(g.Geometria)

	var area float64

	switch geometria {
	case "esfera":
		area = g.getAreaEsfera(&errList)
		break
	case "cubo":
		area = g.getAreaCubo(&errList)
		break
	case "cilindro":
		area = g.getAreaCilindro(&errList)
		break
	case "paralelepipedo":
		area = g.getAreaParalelepipedo(&errList)
		break
	default:
		err := fmt.Sprintf("Geometria %s não encontrada", g.Geometria)
		errList = append(errList, errors.New(err))
		break
	}

	if len(errList) > 0 {
		return 0, errList
	}

	return area, nil
}

func (g Geometria) getAreaEsfera(errList *[]error) float64 {
	if g.Raio == "" {
		err := fmt.Sprintf("Raio da esfera %s não informado", g.Estrutura)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	raio, err := utils.ExtractDecimal(g.Raio)

	if err != nil || raio < 0 {
		err := fmt.Sprintf(
			"Raio da esfera %s inválido, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	area := 4 * 3.14 * raio * raio

	return area
}

func (g Geometria) getAreaCubo(errList *[]error) float64 {
	if g.Lado == "" {
		err := fmt.Sprintf("Lado do cubo %s não informado", g.Estrutura)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	lado, err := utils.ExtractDecimal(g.Lado)

	if err != nil || lado < 0 {
		err := fmt.Sprintf(
			"Lado do cubo '%s' é inválido: %s, deve ser um número positivo e válido",
			g.Estrutura,
			g.Lado,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	area := 6 * lado * lado

	return area
}

func (g Geometria) getAreaCilindro(errList *[]error) float64 {
	if g.Altura == "" || g.RaioBase == "" {
		err := fmt.Sprintf("Altura ou raio da base do cilindro %s não foi informado", g.Estrutura)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	altura, err := utils.ExtractDecimal(g.Altura)

	if err != nil || altura < 0 {
		err := fmt.Sprintf(
			"Altura do cilindro %s é inválida, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	raioBase, err := utils.ExtractDecimal(g.RaioBase)

	if err != nil || raioBase < 0 {
		err := fmt.Sprintf(
			"Raio da base do cilindro %s é inválido, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	area := 2 * 3.14 * raioBase * (altura + raioBase)
	return area
}

func (g Geometria) getAreaParalelepipedo(errList *[]error) float64 {
	if g.Comprimento == "" || g.Largura == "" || g.Altura == "" {
		err := fmt.Sprintf(
			"Comprimento, largura ou altura do paralelepipedo %s não foi informado",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	comprimento, err := utils.ExtractDecimal(g.Comprimento)

	if err != nil || comprimento < 0 {
		err := fmt.Sprintf(
			"Comprimento do paralelepipedo %s é inválido, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	largura, err := utils.ExtractDecimal(g.Largura)

	if err != nil || largura < 0 {
		err := fmt.Sprintf(
			"Largura do paralelepipedo %s é inválida, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	altura, err := utils.ExtractDecimal(g.Altura)

	if err != nil || altura < 0 {
		err := fmt.Sprintf(
			"Altura do paralelepipedo %s é inválida, deve ser um número positivo e válido",
			g.Estrutura,
		)
		*errList = append(*errList, errors.New(err))
		return 0
	}

	area := 2 * (comprimento*largura + comprimento*altura + largura*altura)

	return area
}
