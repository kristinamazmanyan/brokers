var profilePasswordIncorrectMessages = {
    en: "Password is incorrect!",
    hy: "Գաղտնաբառ սխալ է",
    ru: "Неверный пароль!"
}

var profilePasswordCorrectChanges = {
    en: "Password was successfully changed!",
    hy: "Գաղտնաբառ հաջողությամբ փոխվել է",
    ru: "Пароль был успешно изменен!"
}

var profileSavedSuccess = {
    en: "Data successfully updated!",
    hy: "Տվյալները հաջողությամբ թարմացվել են",
    ru: "Данные успешно обновлены!"
}

var STATUS_ACCESS_DENIED = {
    en: "access denied",
    hy: "մուտքը արգելված է",
    ru: "доступ закрыт"
}

var STATUS_NOT_FOUND = {
    en: "not found",
    hy: "չի գտնվել",
    ru: "не найдено"
}

var STATUS_INTERNAL_ERROR = {
    en: "Internal error",
    hy: "Ներքին սխալ",
    ru: "Внутренняя ошибка"
}
var CONNECTION_REFUSED = {
    en: "connection is refused",
    hy: "կապը հրաժարվել է",
    ru: "соединение отказался"
}

var getMessage = function (errorType, lang) {
    if (lang === 'hy') {
        return errorType.hy;
    } else if (lang === 'ru') {
        return errorType.ru;
    } else {
        return errorType.en;
    }

}