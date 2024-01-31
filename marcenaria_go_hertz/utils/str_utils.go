package utils

import (
	"regexp"
	"strconv"
	"strings"
	"unicode"

	"golang.org/x/text/runes"
	"golang.org/x/text/transform"
	"golang.org/x/text/unicode/norm"
)

func NormalizeStr(s string) string {
	t := transform.Chain(norm.NFD, runes.Remove(runes.In(unicode.Mn)), norm.NFC)
	result, _, _ := transform.String(t, s)
	return strings.ToLower(result)
}

func ExtractDecimal(str string) (float64, error) {
	re := regexp.MustCompile(`[^0-9.,]`)
	strNumerico := re.ReplaceAllString(str, "")

	strNumerico = regexp.MustCompile(`,`).ReplaceAllString(strNumerico, ".")

	numero, err := strconv.ParseFloat(strNumerico, 64)
	if err != nil {
		return 0, err
	}

	return numero, nil
}

func FormatCurrency(numero float64) string {
	numeroArredondado := strconv.FormatFloat(numero, 'f', 2, 64)
	numeroFormatado := strings.Replace(numeroArredondado, ".", ",", -1)
	strReais := "R$" + numeroFormatado

	return strReais
}
