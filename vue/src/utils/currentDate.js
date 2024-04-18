export default () => {
    // Get current date and time
    const currentDate = new Date();

    // Format the date and time
    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // Adding 1 because getMonth() returns zero-based month
    const day = ('0' + currentDate.getDate()).slice(-2);
    const hours = ('0' + currentDate.getHours()).slice(-2);
    const minutes = ('0' + currentDate.getMinutes()).slice(-2);

    // Combine the formatted date and time
    const formattedDateTime = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;

    return formattedDateTime;
}