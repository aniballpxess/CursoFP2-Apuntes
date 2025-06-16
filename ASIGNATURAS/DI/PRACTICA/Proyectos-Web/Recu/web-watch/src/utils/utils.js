function getDateTime() {
    const localDateTime = new Date().toISOString().slice(0, 19).split('T'); // stores YYYY-MM-DD and HH:MM:SS
    localDateTime[0] = localDateTime[0].split('-').reverse().join('/'); // from YYYY-MM-DD to DD/MM/YYYY
    return localDateTime;
}
