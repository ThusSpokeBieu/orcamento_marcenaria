using Microsoft.AspNetCore.Hosting.Server.Features;

var builder = WebApplication.CreateBuilder(args);

builder.WebHost.ConfigureKestrel(options =>
{
    options.AllowSynchronousIO = true;
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddOutputCache(
    options => options.AddBasePolicy(builder => builder.Expire(TimeSpan.FromSeconds(120)))
);

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();

AppContext.SetData("GCHeapHardLimit", (ulong)100 * 1024 * 1024);

app.UseHttpsRedirection();

app.UseOutputCache();

app.MapGet("/geometrias", async () => await Task.FromResult("Geometrias está funcionando"))
    .WithName("GetGeometrias")
    .CacheOutput()
    .WithOpenApi();

app.MapGet("/materiais", async () => await Task.FromResult("Materiais está funcionando"))
    .WithName("GetMateriais")
    .CacheOutput()
    .WithOpenApi();

app.MapPost(
        "/orcamento",
        async (String req) => await Task.FromResult("Orcamento está funcionando: " + req)
    )
    .WithName("PostOrcamento")
    .CacheOutput()
    .WithOpenApi();

await app.RunAsync();
