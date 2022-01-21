

function skipUrl(url) {
    $(location).attr('href', url);
}
function sleep(duration) {
    return new Promise(resolve => {
        setTimeout(resolve, duration);
    })
}
async function reloadAfter(time){
    await sleep(time);
    window.location.reload();;
}
