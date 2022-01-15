function adminTabChange(tabNum) {
    if (tabNum == '1') {
        location.href = '/hide/admin/user';
    } else if (tabNum == '2') {
        location.href = '/hide/admin/role';
    } else if (tabNum == '3') {
        location.href = '/hide/admin/resource';
    }
}

function companyTabChange(tabNum) {
    if (tabNum == '1') {
        location.href = '/co/detail';
    } else if (tabNum == '2') {
        location.href = '/co/task/main';
    }
}

function devTabChange(tabNum) {
    if (tabNum == '1') {
        location.href = '/dev/detail';
    } else if (tabNum == '2') {
        location.href = '/dev/toy/main';
    } else if (tabNum == '3') {
        location.href = '/dev/support/manager';
    }
}