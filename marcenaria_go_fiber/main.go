package main

import (
	"fmt"
	"log"
	"marcenaria/models"
	"marcenaria/notification"
	"os"
	"strconv"
	"time"

	"github.com/goccy/go-json"
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/cache"
	"github.com/gofiber/fiber/v2/middleware/idempotency"
	"github.com/gofiber/fiber/v2/utils"
	"github.com/gofiber/swagger"

	_ "marcenaria/docs"
)

func main() {
	if fiber.IsChild() {
		fmt.Printf("[%d] Child\n", os.Getppid())
	} else {
		fmt.Printf("[%d] Master\n", os.Getppid())
	}

	app := fiber.New(fiber.Config{
		Prefork:       true,
		CaseSensitive: false,
		StrictRouting: true,
		ServerHeader:  "Marcenaria",
		AppName:       "Marcenaria App v1.0.0",
		JSONEncoder:   json.Marshal,
		JSONDecoder:   json.Unmarshal,
	})

	app.Use(idempotency.New())

	app.Use(cache.New(cache.Config{
		ExpirationGenerator: func(c *fiber.Ctx, cfg *cache.Config) time.Duration {
			newCacheTime, _ := strconv.Atoi(c.GetRespHeader("Cache-Time", "600"))
			return time.Second * time.Duration(newCacheTime)
		},
		KeyGenerator: func(c *fiber.Ctx) string {
			return utils.CopyString(c.Path() + string(c.Request().Body()))
		},
	}))

	app.Get("/docs/*", swagger.HandlerDefault)

	app.Get("/geometrias", func(c *fiber.Ctx) error {
		return c.JSON(models.Geometrias)
	})

	app.Get("/materiais", func(c *fiber.Ctx) error {
		return c.JSON(models.GetMateriais())
	})

	app.Post("/orcamento", func(c *fiber.Ctx) error {
		movel := new(models.Movel)
		if err := c.BodyParser(movel); err != nil {
			return c.Status(503).SendString(err.Error())
		}

		orcamento, errList := models.NewOrcamento(movel)

		if errList != nil {
			return c.Status(400).JSON(
				notification.NewBadRequest("Algum parâmetro está errado", &errList))
		}

		return c.JSON(orcamento)
	})

	log.Fatal(app.Listen(":8080"))
}
