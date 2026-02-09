<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="sidebar d-flex flex-column p-3">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <span class="fs-4">OOP-NP Admin</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/developers" class="nav-link">Developers</a>
        </li>
        <li>
            <a href="/managers" class="nav-link">Managers</a>
        </li>
        <li>
            <a href="/customers" class="nav-link">Customers</a>
        </li>
        <li>
            <a href="/projects" class="nav-link">Projects</a>
        </li>
    </ul>
    <hr>
</div>

<div class="content">
    <h1>Welcome to Admin Dashboard</h1>
    <p>Select a category from the sidebar to manage your data.</p>

    <#-- Тут буде твій основний контент: таблиці, форми тощо -->
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>