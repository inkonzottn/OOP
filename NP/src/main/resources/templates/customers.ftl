<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customers</title>
</head>
<body>
<h1>Customers list</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Company</th>
        <th>FName</th>
        <th>LName</th>
        <th>Email</th>
        <th>Projects</th>
    </tr>
    </thead>
    <tbody>

    <#if customers??>
        <#list customers as customer>
            <tr>
                <td>${customer.id}</td>
                <td>${customer.companyName}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>
                    <#if customer.projects?? && customer.projects?size gt 0>
                        <ul>
                            <#list customer.projects as project>
                                <li>${project.title} (ID: ${project.id})</li>
                            </#list>
                        </ul>
                    <#else>
                        No projects yet.
                    </#if>
                </td>
            </tr>
        </#list>
    <#else>
        No customers yet.
    </#if>
    </tbody>
</table>
</body>
</html>