package main

import (
	"fmt"
	"log"
	"marcenaria/models/materiais"
	"os"
	"strconv"
	"time"

	"github.com/goccy/go-json"
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/cache"
	"github.com/gofiber/fiber/v2/utils"
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

	app.Use(cache.New(cache.Config{
		ExpirationGenerator: func(c *fiber.Ctx, cfg *cache.Config) time.Duration {
			newCacheTime, _ := strconv.Atoi(c.GetRespHeader("Cache-Time", "600"))
			return time.Second * time.Duration(newCacheTime)
		},
		KeyGenerator: func(c *fiber.Ctx) string {
			return utils.CopyString(c.Path() + string(c.BodyRaw()))
		},
	}))

	app.Get("/geometrias", func(c *fiber.Ctx) error {
		return c.SendString("Geometria está funcionando!")
	})

	app.Get("/materiais", func(c *fiber.Ctx) error {
		return c.JSON(materiais.GetMateriais())
	})

	app.Post("/orcamento", func(c *fiber.Ctx) error {
		return c.SendString("Materiais está funcionando!")
	})

	log.Fatal(app.Listen(":8080"))
}
