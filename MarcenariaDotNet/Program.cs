using MarcenariaDotNet.Dtos;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.ConfigureHttpJsonOptions(options => {
    options.SerializerOptions.IgnoreReadOnlyProperties = true;
    options.SerializerOptions.PropertyNamingPolicy = System.Text.Json.JsonNamingPolicy.SnakeCaseLower;
});

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();

app.UseHttpsRedirection();


app.MapGet("/ping", () => "pong");
app.MapPost("/orcamento", async (MovelDto movel) => await Task.Run(() => Orcamento.From(movel)))
.WithName("PostOrcamento")
.WithOpenApi();

await app.RunAsync();

