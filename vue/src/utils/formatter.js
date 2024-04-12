export function formatTimestamp(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const hours = date.getHours() % 12 || 12; // Convert to 12-hour format
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const period = date.getHours() < 12 ? 'AM' : 'PM';

    return `${year}-${month}-${day} ${hours}:${minutes} ${period}`;
}