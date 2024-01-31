package main

import (
	"context"

	"github.com/cloudwego/hertz/pkg/app"
	"github.com/cloudwego/hertz/pkg/app/server"
	"github.com/cloudwego/hertz/pkg/protocol/consts"

	"marcenaria_go_hertz/models"
)

func main() {
	h := server.Default()

	h.POST("/orcamento", func(c context.Context, ctx *app.RequestContext) {
		var movel models.Movel

		err := ctx.BindJSON(&movel)
		if err != nil {
			ctx.Error(err)
			ctx.Errors.JSON()
			return
		}

		orcamento, errList := models.NewOrcamento(&movel)

		if errList != nil {
			ctx.JSON(
				consts.StatusBadRequest,
				models.NewBadRequest("Algo de errado aconteceu com a requisição", &errList),
			)
			return
		}

		ctx.JSON(consts.StatusOK, &orcamento)
	})

	h.Spin()
}
