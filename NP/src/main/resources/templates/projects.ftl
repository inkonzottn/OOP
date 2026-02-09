<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
</head>
<body>
<h1>Projects list</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Status</th>
        <th>Title</th>
        <th>Customer</th>
        <th>Total cost</th>
        <th>Manager</th>
    </tr>
    </thead>
    <tbody>

    <#if projects??>
        <#list projects as project>
            <tr>
                <td>${project.id}</td>
                <td>${project.description}</td>
                <td>${project.status}</td>
                <td>${project.title}</td>
                <td>
                    <#if project.customer??>
                    ${project.customer.firstName} ${project.customer.lastName}
                        <#else>
                        No customer.
                    </#if>
                </td>
                <td>$ ${project.totalCost}</td>
                <td>
                    <#if project.manager??>
                        ${project.manager.firstName} ${project.manager.lastName}
                    <#else>
                        No manager yet.
                    </#if>
                </td>
            </tr>
        </#list>
    <#else>
        No projects yet.
    </#if>
    </tbody>
</table>
</body>
</html>