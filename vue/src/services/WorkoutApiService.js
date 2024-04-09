import axios from "axios";

const API_URL = 'https://work-out-api1.p.rapidapi.com';

const workoutService = {
    fetchWorkoutsByMuscle: async (muscle) => {
        try {
            const response = await axios.get(`${API_URL}/search`, {

                params: { Muscles: muscle },
                headers: {
                    'X-RapidAPI-Key': '4270d1472cmshb8fe5d0cc5b24bep1f6ad5jsn96cddf245e81',
                    'X-RapidAPI-Host': 'work-out-api1.p.rapidapi.com'
                }
            });

            return response.data;
        } catch (error) {
            console.error('Error fetching workouts:', error);
            throw error;
        }
    }
}

export default workoutService;