$OutputEncoding = [System.Text.Encoding]::UTF8
$BASE_URL = "http://localhost:8080/api/todos"

Write-Host "=== 1. Create Todo ===" -ForegroundColor Green
Invoke-RestMethod -Uri $BASE_URL -Method Post -ContentType "application/json" -Body '{"title":"first todo"}'

Write-Host ""
Write-Host "=== 2. Create Another ===" -ForegroundColor Green
Invoke-RestMethod -Uri $BASE_URL -Method Post -ContentType "application/json" -Body '{"title":"second todo"}'

Write-Host ""
Write-Host "=== 3. Get All ===" -ForegroundColor Green
Invoke-RestMethod -Uri $BASE_URL -Method Get

Write-Host ""
Write-Host "=== 4. Get One (id=1) ===" -ForegroundColor Green
Invoke-RestMethod -Uri "$BASE_URL/1" -Method Get

Write-Host ""
Write-Host "=== 5. Complete (id=1) ===" -ForegroundColor Green
Invoke-RestMethod -Uri "$BASE_URL/1/complete" -Method Patch

Write-Host ""
Write-Host "=== 6. Get All After Complete ===" -ForegroundColor Green
Invoke-RestMethod -Uri $BASE_URL -Method Get

Write-Host ""
Write-Host "=== 7. Delete (id=2) ===" -ForegroundColor Green
Invoke-RestMethod -Uri "$BASE_URL/2" -Method Delete
Write-Host "Deleted"

Write-Host ""
Write-Host "=== 8. Get All After Delete ===" -ForegroundColor Green
Invoke-RestMethod -Uri $BASE_URL -Method Get